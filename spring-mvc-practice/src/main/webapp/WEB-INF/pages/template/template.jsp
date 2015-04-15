<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    </head>
    <body>
    <!-- Start Header -->
    <header role="navigation">
        <tiles:insertAttribute name="header" flush="false" ignore="true" />
    </header>
    <!-- End Header -->

    <!-- Start Content -->
    <div class="wrapper" id="main">
        <tiles:insertAttribute name="content" flush="false" ignore="true" />
    </div>
    <!-- End Content -->

    <!-- Start Header -->
    <footer id="footer" class="bg-pcsmastercard">
        <tiles:insertAttribute name="footer" flush="false" ignore="true" />
    </footer>
    <!-- End Header -->
    </body>
</html>