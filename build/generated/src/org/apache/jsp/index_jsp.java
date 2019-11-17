package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>West England Drivers Association</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <!-- Bootstrap core CSS -->\n");
      out.write("        <link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <!-- Custom styles -->\n");
      out.write("        <link href=\"css/freelancer.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/freelancer.css\" rel=\"stylesheet\">\n");
      out.write("        <!-- Custom fonts  -->\n");
      out.write("        <link href=\"vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/navigationBar.jsp", out, false);
      out.write("  \n");
      out.write("        \n");
      out.write(" <!-- START OF CAROUSEL --> \n");
      out.write("\n");
      out.write("        <div id=\"carouselExampleIndicators\" class=\"carousel slide\" data-ride=\"carousel\">\n");
      out.write("            <ol class=\"carousel-indicators\">\n");
      out.write("                <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"active\"></li>\n");
      out.write("                <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"1\"></li>\n");
      out.write("                <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"2\"></li>\n");
      out.write("            </ol>\n");
      out.write("            <div class=\"carousel-inner\">\n");
      out.write("                <div class=\"carousel-item active\">\n");
      out.write("                    <img src=\"img/NYBanner.jpg\" class=\"d-block w-100\" alt=\"Image failed to load\">\n");
      out.write("                    <!-- banner size is 2560x960 -->\n");
      out.write("                    <div class=\"carousel-caption d-none d-md-block\">\n");
      out.write("                        <h5>We are West England Drivers Association</h5>\n");
      out.write("                        <p>We are a solidarity fund who help our members claim through us rather than your insurance funds, check out more information below</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"carousel-item\">\n");
      out.write("                    <img src=\"img/Michigan.jpg\" class=\"d-block w-100\" alt=\"Image failed to load\">\n");
      out.write("                    <!-- banner size is 2560x960 -->\n");
      out.write("                </div>\n");
      out.write("                <div class=\"carousel-item\">\n");
      out.write("                    <img src=\"img/London.jpg\" class=\"d-block w-100\" alt=\"Image failed to load\">\n");
      out.write("                    <!-- banner size is 2560x960 -->\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <a class=\"carousel-control-prev\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"prev\">\n");
      out.write("                <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                <span class=\"sr-only\">Previous</span>\n");
      out.write("            </a>\n");
      out.write("            <a class=\"carousel-control-next\" href=\"#carouselExampleIndicators\" role=\"button\" data-slide=\"next\">\n");
      out.write("                <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n");
      out.write("                <span class=\"sr-only\">Next</span>\n");
      out.write("            </a>\n");
      out.write("        </div>\n");
      out.write("        <!-- END OF CAROUSEL --> \n");
      out.write("\n");
      out.write("        <!-- START OF JUMOBTRON -->\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <h1 class=\"display-4\">Welcome to West England Drivers Association!</h1>\n");
      out.write("            <p class=\"lead\">We are a solidarity fund who offer you that piece of mind</p>\n");
      out.write("            <hr class=\"my-4\">\n");
      out.write("            <p>Solidarity is the “moral infrastructure” of social insurance arrangements that protect citizens against financial risks of illness: costs of medical care (health insurance) and loss of income (disability insurance). Although these arrangements have both met reforms, the effects of these reforms on the two forms of insurance have not yet been compared. This article presents a comparative analysis of these reforms’ impact on solidarity since the 1980s in the Netherlands. It develops an analytical framework, distinguishing coverage and financing dimensions, and concludes that the reforms affected several solidarity dimensions and that the effects were partly different in health insurance and disability insurance.</p>\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("        <!--END OF JUMBOTRON -->\n");
      out.write("                \n");
      out.write("        <!-- START OF INFO ABOUT US -->\n");
      out.write("            <div class=\"container\">\n");
      out.write("\n");
      out.write("                <h2 class=\"text-center text-uppercase text-secondary mb-0\">Our team</h2>\n");
      out.write("                <hr class=\"star-dark mb-5\">\n");
      out.write("\n");
      out.write("                <!-- Team Members Row -->\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-lg-4 col-sm-6 text-center mb-4\">\n");
      out.write("                        <img class=\"rounded-circle img-fluid d-block mx-auto\" src=\"img/employeePics/jordan.jpg\" width=\"200px\" alt=\"\">\n");
      out.write("                        <h3>Jordan Draper</h3>\n");
      out.write("                        <h3><small>CEO</small></h3>\n");
      out.write("                        <p>Our company vision is to be seen as the best, if we can reach the right audience, people will find out we already are</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 col-sm-6 text-center mb-4\">\n");
      out.write("                        <img class=\"rounded-circle img-fluid d-block mx-auto\" src=\"img/employeePics/andreas.jpg\" width=\"200px\" alt=\"\">\n");
      out.write("                        <h3>Andreas Dane</h3>\n");
      out.write("                        <h3><small>Operations manager</small></h3>\n");
      out.write("                        <p>Whether you're looking to invest of become a client, we will be with you every step of you journey</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 col-sm-6 text-center mb-4\">\n");
      out.write("                        <img class=\"rounded-circle img-fluid d-block mx-auto\" src=\"img/employeePics/alex.jpg\" width=\"200px\" alt=\"\">\n");
      out.write("                        <h3>Alex Press</h3>\n");
      out.write("                        <h3><small>Systems Director</small></h3>\n");
      out.write("                        <p>We are paving the way in intelligent systems, contact me for any questions about our platform</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 col-sm-6 text-center mb-4\">\n");
      out.write("                        <img class=\"rounded-circle img-fluid d-block mx-auto\" src=\"img/employeePics/jack.jpg\" width=\"200px\" alt=\"\">\n");
      out.write("                        <h3>Jack Grainger</h3>\n");
      out.write("                        <h3><small>Lead developer</small></h3>\n");
      out.write("                        <p>West England Drivers Association will be the today of tomorrow</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-lg-4 col-sm-6 text-center mb-4\">\n");
      out.write("                        <img class=\"rounded-circle img-fluid d-block mx-auto\" src=\"img/employeePics/max.jpg\" width=\"200px\" alt=\"\">\n");
      out.write("                        <h3>Max Cole</h3>\n");
      out.write("                        <h3><small>Database architect</small></h3>\n");
      out.write("                        <p>The passion we all share for our work here at West England Drivers Association will be the success factor within the business</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- END OF INFO ABOUT US --> \n");
      out.write("\n");
      out.write("            <!-- START footer -->\n");
      out.write("\n");
      out.write("        <!-- Footer -->\n");
      out.write("        <footer class=\"footer text-center\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-4 mb-5 mb-lg-0\">\n");
      out.write("                        <h4 class=\"text-uppercase mb-4\">Location</h4>\n");
      out.write("                        <p class=\"lead mb-0\">Bristol\n");
      out.write("                            <br>United Kingdom</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-4 mb-5 mb-lg-0\">\n");
      out.write("                        <h4 class=\"text-uppercase mb-4\">Our social platform</h4>\n");
      out.write("                        <ul class=\"list-inline mb-0\">\n");
      out.write("                            <li class=\"list-inline-item\">\n");
      out.write("                                <a class=\"btn btn-outline-light btn-social text-center rounded-circle\" href=\"\">\n");
      out.write("                                    <i class=\"fab fa-fw fa-facebook-f\"></i>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"list-inline-item\">\n");
      out.write("                                <a class=\"btn btn-outline-light btn-social text-center rounded-circle\" href=\"\">\n");
      out.write("                                    <i class=\"fab fa-fw fa-google-plus-g\"></i>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"list-inline-item\">\n");
      out.write("                                <a class=\"btn btn-outline-light btn-social text-center rounded-circle\" href=\"\">\n");
      out.write("                                    <i class=\"fab fa-fw fa-twitter\"></i>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                            <li class=\"list-inline-item\">\n");
      out.write("                                <a class=\"btn btn-outline-light btn-social text-center rounded-circle\" href=\"\">\n");
      out.write("                                    <i class=\"fab fa-fw fa-linkedin-in\"></i>\n");
      out.write("                                </a>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-4\">\n");
      out.write("                        <h4 class=\"text-uppercase mb-4\">About West England Drivers Association</h4>\n");
      out.write("                        <p class=\"lead mb-0\">We strive to provide the best service, if you have any feedback, good or bad, please reach out to our\n");
      out.write("                            <a href=\"#\">  support team</a>.</p>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("\n");
      out.write("        <!-- copyright -->\n");
      out.write("        <div class=\"copyright py-4 text-center text-white\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <small>Copyright &copy; West England Driver Association 2019</small>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- END footer -->\n");
      out.write("\n");
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
