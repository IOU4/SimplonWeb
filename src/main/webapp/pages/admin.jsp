<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <title>Home - smiplonline</title>
    <jsp:include page="/components/headers.jsp" />
    <script defer src="https://unpkg.com/alpinejs"></script>
  </head>

  <body class="px-3 ">
    <jsp:include page="/components/navbar.jsp" />
    <main
      x-data="{tab: 'students', active: 'inline-block p-4 text-blue-600 rounded-t-lg border-b-2 border-blue-600 active dark:text-blue-500 dark:border-blue-500', inactive:'inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300'}">
      <nav
        class="text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:text-gray-400 dark:border-gray-700">
        <ul class="flex flex-wrap -mb-px">
          <li class="mr-2">
            <a x-on:click.prevent="tab = 'students'" :class="tab === 'students' ? active: inactive" href="#"
              class="">Students</a>
          </li>
          <li class="mr-2">
            <a x-on:click.prevent="tab = 'briefs'" :class="tab === 'briefs' ? active: inactive" href="#" class=""
              aria-current="page">Briefs</a>
          </li>
          <li class="mr-2">
            <a x-on:click.prevent="tab = 'instructors'" :class="tab === 'instructors' ? active: inactive" href="#"
              class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300">instructors</a>
          </li>
          <li class="mr-2">
            <a x-on:click.prevent="tab = 'promos'" :class="tab === 'promos' ? active: inactive" href="#"
              class="inline-block p-4 rounded-t-lg border-b-2 border-transparent hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300">promos</a>
          </li>
        </ul>
      </nav>
      <section x-show="tab === 'students'">
        <c:set var="students" value="${requestScope.students}" />
        <jsp:include page="/components/studentsTable.jsp" />
      </section>
      <section x-show="tab === 'briefs'">
        <c:set var="briefs" value="${requestScope.briefs}" />
        <jsp:include page="/components/briefsTable.jsp" />
      </section>
      <section x-show="tab === 'instructors'">
        <c:set var="instructors" value="${requestScope.instructors}" />
        <jsp:include page="/components/instructorsTable.jsp" />
      </section>
      <section x-show="tab === 'promos'">
        <c:set var="promos" value="${requestScope.promos}" />
        <jsp:include page="/components/promosTable.jsp" />
      </section>
    </main>
    <jsp:include page="/components/footer.jsp" />
    <script src="https://unpkg.com/flowbite@1.5.3/dist/flowbite.js"></script>
  </body>

  </html>
