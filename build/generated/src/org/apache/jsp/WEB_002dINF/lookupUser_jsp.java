package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class lookupUser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <title>West England Drivers Association</title>\n");
      out.write("        <link href=\"css/fixed-two-column.css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("            <div id=\"header\">\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/navigationBar.jsp", out, false);
      out.write(" \n");
      out.write("            </div>\n");
      out.write("            <div id=\"main\">\n");
      out.write("                <div id=\"menu\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/adminSidePanel.jsp", out, false);
      out.write(" \n");
      out.write("                </div>\n");
      out.write("                <div id=\"content\">\n");
      out.write("                    <h2>Lookup user profiles</h2>\n");
      out.write("                    <form action=\"AdminService.do\" method=\"POST\">\n");
      out.write("                        <div>\n");
      out.write("                            Enter an email to begin: <input type=\"text\" name=\"lookupemail\" value=\"\">\n");
      out.write("                            <input name=\"tbl\" type=\"submit\" id=\"claimidbutton\" class=\"form-submit\" value=\"Show user information\"/>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div>\n");
      out.write("                            Email: <input type=\"text\" name=\"lookupemail\" value=\"");
      out.print((String) (request.getAttribute("lookupemail")));
      out.write("\"readonly>                           \n");
      out.write("                            Username <input type=\"text\" name=\"lookupusername\" value=\"");
      out.print((String) (request.getAttribute("lookupusername")));
      out.write("\"readonly>\n");
      out.write("                            Full name: <input type=\"text\" name=\"lookupfullname\" value=\"");
      out.print((String) (request.getAttribute("lookupfullname")));
      out.write("\"readonly>\n");
      out.write("                            Date of Birth: <input type=\"text\" name=\"lookupdob\" value=\"");
      out.print((String) (request.getAttribute("lookupdob")));
      out.write("\"readonly>\n");
      out.write("                            Date of Registration: <input type=\"text\" name=\"lookupdateofregistration\" value=\"");
      out.print((String) (request.getAttribute("lookupdateofregistration")));
      out.write("\"readonly>\n");
      out.write("                            Outstanding Balance: <input type=\"text\" name=\"lookupoutstandingbalance\" value=\"");
      out.print((String) (request.getAttribute("lookupoutstandingbalance")));
      out.write("\"readonly>                            \n");
      out.write("                            Balance: <input type=\"text\" name=\"lookupbalanace\" value=\"");
      out.print((String) (request.getAttribute("lookupbalance")));
      out.write("\"readonly>\n");
      out.write("                            Address: <input type=\"text\" name=\"lookupaddress\" value=\"");
      out.print((String) (request.getAttribute("lookupaddress")));
      out.write("\"readonly>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                            \n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <p>");
      out.print((String) (request.getAttribute("message")));
      out.write("</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"clearer\"></div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
