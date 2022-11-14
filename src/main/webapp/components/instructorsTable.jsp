<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:choose>
    <c:when test="${instructors.size() == 0 || instructors == null}">
      <p classs="text-gray-200 italic">no instructors found!</p>
    </c:when>
    <c:otherwise>
      <h3 class="text-2xl font-bold py-4 ">Instructors:</h3>
      <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" class="py-3 px-6"> Name </th>
              <th scope="col" class="py-3 px-6"> Current Promo </th>
              <th scope="col" class="py-3 px-6"> Action </th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${instructors}" var="instructor">
              <tr
                class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                <th scope="row" class="flex items-center py-4 px-6 text-gray-900 whitespace-nowrap dark:text-white">
                  <div class="pl-3">
                    <div class="text-base font-semibold">${instructor.getName()}</div>
                    <div class="font-normal text-gray-500">${instructor.getEmail()}</div>
                  </div>
                </th>
                <td class="py-4 px-6">
                  <c:choose>
                    <c:when test="${instructor.getCurrentPromo() == null}">
                      <div class="flex items-center">
                        <span class="text-gray-400 italic">not assigned</span>
                      </div>
                    </c:when>
                    <c:otherwise> ${instructor.getCurrentPromo().getName()} </c:otherwise>
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
    </c:otherwise>
  </c:choose>
