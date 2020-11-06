$.ajax({
   type: "GET",
   dataType: "json",
   url: "/api/users",
   success: function(data){ 
    $('body').append($('<table>'));
    $('body').append($('<thead>'));
        $('body').append($('<tr>'));
            $('body').append($('<th>ID</th>'));
            $('body').append($('<th>Name</th>'));
            $('body').append($('<th>Email</th>'));
            $('body').append($('<th>Edit</th>'));
            $('body').append($('<th>Delete</th>'));
        $('body').append($('</tr>'));
    $('body').append($('</thead>'));
    $('body').append($('<tbody>'));
        $.each(data, function(index, user) {
            $('body').append($('<tr>'));
            $('body').append($('<td>', {text: user.id}, '</td>'));
            $('body').append($('<td>', {text: user.name}, '</td>'));
            $('body').append($('<td>', {text: user.email}, '</td>'));
            $('body').append($('<td><button onclick="editUser(\'' + user.id + '\')">Edit</button></td>'));
            $('body').append($('<td><button onclick="deleteUser(\'' + user.id + '\')">Delete</button></td>'));
            $('body').append($('</tr>'));
         });
    $('body').append($('</tbody>'));
    $('body').append($('</table>'));
    $('body').append($('<p><button onclick="addUser()">Add a new user</button></p>'));
   }
});

function addUser(id){
    console.log("add " + id);
    var link = "/signup";
    window.location.href = link
}

function editUser(id){
    console.log("edit " + id);
    var link = "/edit/"+id;
    window.location.href = link
}

function deleteUser(id){
    console.log("delete " + id);
    var link = "/delete/"+id;
    window.location.href = link
}
