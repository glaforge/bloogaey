
request.code  = request.getAttribute("javax.servlet.error.status_code");
request.ex    = request.getAttribute("javax.servlet.error.exception");
request.msg   = request.getAttribute("javax.servlet.error.message");

forward '/WEB-INF/pages/error.gtpl'