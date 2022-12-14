<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <div x-data="{showAdd: false, showDelete: false, showEdit: false}">
    <div class="flex items-center w-full justify-between my-4">
      <h3 class="text-2xl font-bold py-4 ">Promos:</h3>
      <button type="button" @click="showAdd = true"
        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">add
        new promo</button>
    </div>
    <c:choose>
      <c:when test="${promos.size() == 0 || promos == null}">
        <p classs="text-gray-200 italic">no promos found!</p>
      </c:when>
      <c:otherwise>
        <div class="overflow-x-auto relative shadow-md sm:rounded-lg">
          <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
              <tr>
                <th scope="col" class="py-3 px-6"> Name </th>
                <th scope="col" class="py-3 px-6"> Instructor </th>
                <th scope="col" class="py-3 px-6"> Students Count </th>
                <th scope="col" class="py-3 px-6"> Action </th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${promos}" var="promo">
                <tr
                  class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                  <th scope="row" class="flex items-center py-4 px-6 text-gray-900 whitespace-nowrap dark:text-white">
                    <div class="pl-3">
                      <div class="text-base font-bold">${promo.getName()}</div>
                    </div>
                  </th>
                  <td class="py-4 px-6">
                    <c:choose>
                      <c:when test="${promo.getInstructor() == null}">
                        <span class="text-gray-400 italic">no instructor</span>
                      </c:when>
                      <c:otherwise> ${promo.getInstructor().getName()} </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="py-4 px-6">
                    <span class="text-gray-400"> ${promo.getStudentsCount()} </span>
                  </td>
                  <td class=" py-4 px-6 space-x-2">
                    <!-- Modal toggle -->
                    <a href="#" type="button" data-modal-toggle="editUserModal"
                      class="font-bold text-blue-600 dark:text-blue-500 hover:underline">Edit</a>
                    <button @click="showDelete = true" type="button" data-modal-toggle="delelteUserModal"
                      class="font-bold text-red-300 eark:text-red-300 hover:underline">Delete</button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:otherwise>
    </c:choose>

    <!-- delete modal -->
    <div x-show="showDelete" tabindex="-1"
      class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-modal md:h-full flex bg-gray-700 bg-opacity-50">
      <div class="relative p-4 w-full max-w-md h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative p-4 text-center bg-white rounded-lg shadow dark:bg-gray-800 sm:p-5">
          <button type="button" @click="showDelete = false"
            class="text-gray-400 absolute top-2.5 right-2.5 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white">
            <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd"
                d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                clip-rule="evenodd"></path>
            </svg>
            <span class="sr-only">Close modal</span>
          </button>
          <svg class="text-gray-400 dark:text-gray-500 w-11 h-11 mb-3.5 mx-auto" aria-hidden="true" fill="currentColor"
            viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
              d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
              clip-rule="evenodd"></path>
          </svg>
          <p class="mb-4 text-gray-500 dark:text-gray-300">Are you sure you want to delete this promo?</p>
          <div class="flex justify-center items-center space-x-4">
            <button type="button" @click="showDelete = false"
              class="py-2 px-3 text-sm font-medium text-gray-500 bg-white rounded-lg border border-gray-200 hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-primary-300 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
              Cancel
            </button>
            <button type="submit"
              class="py-2 px-3 text-sm font-medium text-center text-white bg-red-600 rounded-lg hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 dark:bg-red-500 dark:hover:bg-red-600 dark:focus:ring-red-900">
              Save
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- add modal -->
    <div x-show="showAdd" tabindex="-1"
      class="overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-modal md:h-full flex bg-gray-700 bg-opacity-50">
      <div class="relative p-4 w-full max-w-md h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative p-4 bg-white rounded-lg shadow dark:bg-gray-800 sm:p-5">
          <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">add new student:</h3>
          <form class="space-y-6" action="#" method="post">
            <div>
              <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"> Promo name
              </label>
              <input type="text" name="name" id="name"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                placeholder="example name" required>
            </div>
            <div class="flex justify-center items-center space-x-4">
              <button type="button" @click="showAdd = false"
                class="py-2 px-3 text-sm font-medium text-gray-500 bg-white rounded-lg border border-gray-200 hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-primary-300 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">
                cancel </button>
              <button type="submit"
                class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Save </button>
            </div>
            <input type="hidden" name="action" value="addPromo">
          </form>
        </div>
      </div>
    </div>
  </div>
