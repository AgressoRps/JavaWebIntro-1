$(document).ready(function () {
});

AircraftController = {
    repairAircraft(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/aircraft/' + id.value + '/repair';
        requestController(urlRequest, id.value);
    },
    available(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/aircraft/' + id.value + '/available';
        requestController(urlRequest, id.value);
    },
    deleteAircraft(){
        var id = document.getElementById('id');
        var urlRequest = '/admin/aircraft/' + id.value + '/delete';
        deleteController(urlRequest, id.value);
    }
};
