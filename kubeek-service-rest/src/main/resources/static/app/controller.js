var app = angular.module('kubeek-server-js', [])
    app.controller('AppController', function($scope, $http){

    $scope.send = function() {

        var data = {
            "message"   : $scope.kubeek.message,
            "yPosition" : 12,
            "delayTime" : 15000,
            "redColor"  : 0,
            "greenColor": 255,
            "blueColor" : 0
        };

        $http.post('/api/v1/text/scrolltext', data).success(function(response) {

            angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
            angular.element(document.querySelector("#divstatus")).addClass("alert-success");
            $scope.statusmsg="Message has been sent successfully! " + response.id;
            $("#divstatus").fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );

        }).error(function (response) {
            angular.element(document.querySelector("#divstatus")).css("visibility", "visible");
            angular.element(document.querySelector("#divstatus")).addClass("alert-danger");
            $scope.statusmsg= response.error;
            $("#divstatus").fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );

        });
        $scope.kubeek="";

    };
});



