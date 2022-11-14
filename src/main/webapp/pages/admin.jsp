<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <title>Home - smiplonline</title>
    <jsp:include page="/components/headers.jsp" />
  </head>

  <body class="px-3 ">
    <jsp:include page="/components/navbar.jsp" />
    <main>
      <section>
        <c:set var="students" value="${requestScope.students}" />
        <c:choose>
          <c:when test="${students.size() == 0 || students == null}">
            <p classs="text-gray-200 italic">no students found!</p>
          </c:when>
          <c:otherwise>
            <h3 class="text-2xl font-bold py-4 ">Students:</h3>
            <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
              <jsp:include page="/components/studentsTable.jsp" />
            </div>
          </c:otherwise>
        </c:choose>
      </section>
      <section>
        <c:set var="instructors" value="${requestScope.instructors}" />
        <c:choose>
          <c:when test="${instructors.size() == 0 || instructors == null}">
            <p classs="text-gray-200 italic">no instructors found!</p>
          </c:when>
          <c:otherwise>
            <h3 class="text-2xl font-bold py-4 ">Instructors:</h3>
            <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
              <jsp:include page="/components/instructorsTable.jsp" />
            </div>
          </c:otherwise>
        </c:choose>
      </section>
      <c:set var="promos" value="${requestScope.promos}" />
      <c:choose>
        <c:when test="${promos.size() == 0 || promos == null}">
          <p classs="text-gray-200 italic">no promos found!</p>
        </c:when>
        <c:otherwise>
          <h3 class="text-2xl font-bold py-4 ">promos:</h3>
          <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
            <jsp:include page="/components/promosTable.jsp" />
          </div>
        </c:otherwise>
      </c:choose>
      </section>
    </main>
    <jsp:include page="/components/footer.jsp" />
    <script src="https://unpkg.com/flowbite@1.5.3/dist/flowbite.js"></script>
  </body>

  </html>
