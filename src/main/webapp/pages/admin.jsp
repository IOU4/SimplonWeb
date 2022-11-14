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
      <h3 class="text-2xl font-bold py-4 ">Students:</h3>
      <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" class="py-3 px-6"> Name </th>
              <th scope="col" class="py-3 px-6"> Promo </th>
              <th scope="col" class="py-3 px-6"> Instructor </th>
              <th scope="col" class="py-3 px-6"> Action </th>
            </tr>
          </thead>
          <tbody>
            <c:set var="students" value="${requestScope.students}" />
            <c:forEach items="${students}" var="student">
              <tr
                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="flex items-center py-4 px-6 text-gray-900 whitespace-nowrap dark:text-white">
                  <div class="pl-3">
                    <div class="text-base font-semibold">${student.getName()}</div>
                    <div class="font-normal text-gray-500">${student.getEmail()}</div>
                  </div>
                </th>
                <td class="py-4 px-6">
                  <c:choose>
                    <c:when test="${student.getPromo() == null}">
                      <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full bg-red-400 mr-2"></div><span class="text-gray-400">No
                          promo</span>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full bg-green-400 mr-2"></div>${student.getPromo().getName()}
                      </div>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class="py-4 px-6">
                  <c:choose>
                    <c:when test="${student.getPromo() == null}">
                      <div class="flex items-center">
                        <div class="h-2.5 w-2.5 rounded-full bg-red-400 mr-2"></div><span class="text-gray-400"> -
                        </span>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <c:choose>
                        <c:when test="${student.getPromo().getInstructor() == null}">
                          <div class="flex items-center">
                            <div class="h-2.5 w-2.5 rounded-full bg-red-400 mr-2"></div><span class="text-gray-400">No
                              Instructor</span>
                          </div>
                        </c:when>
                        <c:otherwise>
                          <div class="flex items-center">
                            <div class="h-2.5 w-2.5 rounded-full bg-green-400 mr-2"></div><span
                              class="text-gray-400">${student.getPromo().getInstructor().getName()}</span>
                          </div>
                        </c:otherwise>
                      </c:choose>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class=" py-4 px-6 space-x-2">
                  <!-- Modal toggle -->
                  <a href="#" type="button" data-modal-toggle="editUserModal"
                    class="font-bold text-blue-600 dark:text-blue-500 hover:underline">Edit</a>
                  <a href="#" type="button" data-modal-toggle="delelteUserModal"
                    class="font-bold text-red-300 eark:text-red-300 hover:underline">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </main>
    <jsp:include page="/components/footer.jsp" />
    <script src="https://unpkg.com/flowbite@1.5.3/dist/flowbite.js"></script>
  </body>

  </html>
