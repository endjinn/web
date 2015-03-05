Handlebars.registerHelper('stage', function(stageToRender, stageText, options) {
    var classToAdd = (stageToRender === this['current-stage']) ? "selected" : "";
    var stageContext = {};
    stageContext.stageClass = classToAdd;
    stageContext.stageText = stageText;
           
    return new Handlebars.SafeString(options.fn(stageContext));
});



