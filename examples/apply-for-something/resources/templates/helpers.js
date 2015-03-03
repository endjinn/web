Handlebars.registerHelper('stage', function(stageToRender, options) {
    var classToAdd = (stageToRender === this.stage) ? "class='selected'" : ""; 
    return new Handlebars.SafeString(        
        '<li ' + classToAdd + '>'
            + options.fn(this)
            + '</li>');
});



