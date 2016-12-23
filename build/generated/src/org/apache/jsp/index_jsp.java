package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/jspf/includes.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=1,initial-scale=1,user-scalable=1\" />\r\n");
      out.write("        <title>GTAPROS</title>\r\n");
      out.write("        ");
      out.write("<link href=\"http://fonts.googleapis.com/css?family=Lato:100italic,100,300italic,300,400italic,400,700italic,700,900italic,900\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"./bootstrap/css/bootstrap.min.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/styles.css\" />");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        <section class=\"container\">\r\n");
      out.write("            <section class=\"login-form\">\r\n");
      out.write("                <form method=\"post\" action=\"login\" role=\"login\">\r\n");
      out.write("                    <img src=\"./images/logo.png\" class=\"img-responsive\" alt=\"\" />\r\n");
      out.write("                    <input type=\"email\" name=\"email\" placeholder=\"Email\" required class=\"form-control input-lg\" />\r\n");
      out.write("                    <input type=\"password\" name=\"password\" placeholder=\"Password\" required class=\"form-control input-lg\" />\r\n");
      out.write("                    <button type=\"submit\" name=\"go\" class=\"btn btn-lg btn-primary btn-block\">Entrar</button>\r\n");
      out.write("                    <div>\r\n");
      out.write("                        <a href=\"newUser.jsp\">Create account</a> or <a href=\"resetPass.jsp\">reset password</a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </form>\r\n");
      out.write("                <div class=\"form-links\">\r\n");
      out.write("                    <a href=\"index.jsp\">www.gtapros.com</a>\r\n");
      out.write("                </div>\r\n");
      out.write("            </section>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
