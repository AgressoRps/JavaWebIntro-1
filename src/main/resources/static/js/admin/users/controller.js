$(document).ready(function () {
});

UserController = {
    blockUser(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/users/' + id.value + '/block';
        requestController(urlRequest, id.value);
    },
    unblockUser(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/users/' + id.value + '/unblock';
        requestController(urlRequest, id.value);
    },
    deleteUser(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/users/' + id.value + '/delete';
        deleteController(urlRequest, id.value);
    }
};
