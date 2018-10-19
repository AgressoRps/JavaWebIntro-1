$(document).ready(function () {
});

PilotController = {
    flightPilot(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/pilots/' + id.value + '/flight';
        requestController(urlRequest, id.value);
    },
    availablePilot(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/pilots/' + id.value + '/available';
        requestController(urlRequest, id.value);
    },
    deletePilot(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/pilots/' + id.value + '/delete';
        deleteController(urlRequest, id.value);
    }
};
