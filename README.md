# endjinn/web

This is a library that helps you to compose web applications.

The philosophy is that everything should be declarative and declared in the templates themselves, because this is where the centre of gravity is when thinking about assembling and composing a web application.

It is particularly suited to multi-page web applications which have a sequence, for example a lengthy application process, or checkout type activity where there are multiple "stages".

The idea is to try to bring some kind of model to this which then prevents having to do too much boilerplate on the server.

Finally, the ideal is that much of the templating and structure can be adapted to purely browser based control flow, such that the application can seamlessly adapt to being executed more on the browser.

Some key ideas I hope to incorporate are:

- Custom elements in HTML5 to define "components"
- Independant of templating engine (althought moustache syntax as default)
- Use of template element in html5 to define templates

To achieve these things, I think that the source files for the template would be in HTML5 but on the server they may get processed to expand the elements so that the actual HTML recieved doesn't include custom elements. Any of this work that can be pushed to the browser will be done so.

