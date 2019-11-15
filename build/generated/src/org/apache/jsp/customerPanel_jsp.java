package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class customerPanel_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>West England Drivers Association</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("\n");
      out.write("        <!-- Main css -->\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/customChanges.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/navigationBar.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <main class=\"pageContainer\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"sidebar text-center\">\n");
      out.write("                    <img src=\"https://s3.amazonaws.com/uifaces/faces/twitter/mantia/128.jpg\" class=\"img-circle\">\n");
      out.write("                    <h3>Customer Name</h3>\n");
      out.write("                    <!-- buttons below -->\n");
      out.write("                    <form method=\"get\" action=\"\">\n");
      out.write("                        <input name=\"button\" type=\"submit\" class=\"btn btn-secondary\" value=\"Check outstanding balance\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <form method=\"get\" action=\"\">\n");
      out.write("                        <input name=\"button\" type=\"submit\" class=\"btn btn-secondary\" value=\"List all payments and claims to date\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <form method=\"get\" action=\"\">\n");
      out.write("                        <input name=\"button\" type=\"submit\" class=\"btn btn-secondary\" value=\"Make a payment\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <form method=\"get\" action=\"\">\n");
      out.write("                        <input name=\"button\" type=\"submit\" class=\"btn btn-secondary\" value=\"Submit a claim\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <form method=\"POST\" action=\"CustomerService.do\">\n");
      out.write("                        <input name=\"tbl\" type=\"submit\" class=\"btn btn-secondary\" value=\"List Users\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <!-- end of buttons -->\n");
      out.write("                </div>\n");
      out.write("                <div class=\"content\">\n");
      out.write("                    <h1>Title</h1>\n");
      out.write("                    \n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- JS -->\n");
      out.write("        <script src=\"vendor/jquery/jquery.min.js\"></script>\n");
      out.write("        <script src=\"vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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
