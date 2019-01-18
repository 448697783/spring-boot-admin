window.onload = () => {

  const buildSystemAsync = async (baseUrl) => {
    try {
    	const baseUrl='http://127.0.0.1:8080/'
      const configUIResponse = await fetch(baseUrl + "/swagger-resources/configuration/ui");
      const configUI = await configUIResponse.json();

      const configSecurityResponse = await fetch(baseUrl + "/swagger-resources/configuration/security");
      const configSecurity = await configSecurityResponse.json();

      const resourcesResponse = await fetch(baseUrl + "/swagger-resources");
      const resources = await resourcesResponse.json();
      resources.forEach(resource => {
        resource.url = baseUrl + resource.url;
      });

      window.ui = getUI(baseUrl, resources, configUI, configSecurity);
    } catch (e) {
      const retryURL = await prompt(
        "Unable to infer base url. This is common when using dynamic servlet registration or when" +
        " the API is behind an API Gateway. The base url is the root of where" +
        " all the swagger resources are served. For e.g. if the api is available at http://127.0.0.1:8080/api/v2/api-docs" +
        " then the base url is http://example.org/api/. Please enter the location manually: ",
        window.location.href);

      return buildSystemAsync(retryURL);
    }
  };

  const getUI = (baseUrl, resources, configUI, configSecurity) => {
    const ui = SwaggerUIBundle({
      /*--------------------------------------------*\
       * Core
      \*--------------------------------------------*/
      configUrl: null,
      dom_id: "#swagger-ui",
      dom_node: null,
      spec: {},
      url: "http://127.0.0.1:8081/",
      urls: resources,
      /*--------------------------------------------*\
       * Plugin system
      \*--------------------------------------------*/
      layout: "StandaloneLayout",
      plugins: [
        SwaggerUIBundle.plugins.DownloadUrl
      ],
      presets: [
        SwaggerUIBundle.presets.apis,
        SwaggerUIStandalonePreset
      ],
      /*--------------------------------------------*\
       * Display
      \*--------------------------------------------*/
      deepLinking: configUI.deepLinking,
      displayOperationId: configUI.displayOperationId,
      defaultModelsExpandDepth: configUI.defaultModelsExpandDepth,
      defaultModelExpandDepth: configUI.defaultModelExpandDepth,
      defaultModelRendering: configUI.defaultModelRendering,
      displayRequestDuration: configUI.displayRequestDuration,
      docExpansion: configUI.docExpansion,
      filter: configUI.filter,
      maxDisplayedTags: configUI.maxDisplayedTags,
      operationsSorter: configUI.operationsSorter,
      showExtensions: configUI.showExtensions,
      tagSorter: configUI.tagSorter,
      /*--------------------------------------------*\
       * Network
      \*--------------------------------------------*/
      oauth2RedirectUrl: baseUrl + "/webjars/springfox-swagger-ui/oauth2-redirect.html",
      requestInterceptor: (a => a),
      responseInterceptor: (a => a),
      showMutatedRequest: true,
      validatorUrl: configUI.validatorUrl,
      /*--------------------------------------------*\
       * Macros
      \*--------------------------------------------*/
      modelPropertyMacro: null,
      parameterMacro: null,
    });

    ui.initOAuth({
      /*--------------------------------------------*\
       * OAuth
      \*--------------------------------------------*/
      clientId: configSecurity.clientId,
      clientSecret: configSecurity.clientSecret,
      realm: configSecurity.realm,
      appName: configSecurity.appName,
      scopeSeparator: configSecurity.scopeSeparator,
      additionalQueryStringParams: configSecurity.additionalQueryStringParams,
      useBasicAuthenticationWithAccessCodeGrant: configSecurity.useBasicAuthenticationWithAccessCodeGrant,
    });

    return ui;
  };

  const getBaseURL = () => {
    const urlMatches = /(.*)\/swagger-ui.html.*/.exec(window.location.href);
    return urlMatches[1];
  };

  /* Entry Point */
  (async () => {
    await buildSystemAsync(getBaseURL());
  })();
  $(".try-out__btn").live('click', function(e){
	  if($(this).parent().parent().children().length<=2){
	  	$(this).parent().parent().append("&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Add_Test'>Add Test</button></div>");
	  	$(this).parent().parent().append("&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Random_Test'>Random Test</button></div>");
	  	$(this).parent().parent().append("&nbsp;&nbsp;<div class='try-out'><button class='btn try-out__btn Test_List'>Test List</button></div>");
  	  }
  });
  
  $(".Test_List").live('click', function(e){
//	  	alert($(this).parent().parent().parent().parent().find("textarea[class='body-param__text']").val());
	  	var parameter = $(this).parent().parent().parent().parent().find("td[class='col parameters-col_name']");
	  	alert(parameter.length);
	  	for(var i=0;i<parameter.length;i++){
	  		alert($(parameter[i]).find("div[class='parameter__name']").text());
	  		alert($(parameter[i]).find("div[class='parameter__type']"));
	  		alert($(parameter[i]).find("div[class='parameter__deprecated']"));
	  		alert($(parameter[i]).find("div[class='parameter__in']"));
	  	}
//		 $(this).parent().parent().parent().append("<div class='try-out'><button class='btn try-out__btn Test_List'>Test List</button></div>");
	  
  });

  
  

};
