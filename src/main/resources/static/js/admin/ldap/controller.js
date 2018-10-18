$(document).ready(function () {

});

LdapController = {
    deactivate(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/ldap/' + id.value + '/deactivate';
        requestController(urlRequest, id.value);
    },
    activate(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/ldap/' + id.value + '/activate';
        requestController(urlRequest, id.value);
    },
    deleteLdap(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/ldap/' + id.value + '/delete';
        deleteController(urlRequest, id.value);
    }
};
