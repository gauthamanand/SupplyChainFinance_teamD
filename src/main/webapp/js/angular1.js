
                                                
                                                    

                                                    var app = angular.module('MyApp', [])
                                                    app.controller('MyController', function ($scope, $http, $window) {
                                                        
                                                    $scope.registerUser = function (value) {

                                                    var data = [{
                                                    tid1: value
                                                    }];
                                                    
                                                    //Call the services
                                                    console.log("hi");
                                                    $http.get('http://localhost:8080', data).then(function (response) {
                                                    if (response.data)
                                                        $scope.msg = "Post Data Submitted Successfully!";
                                                    },
                                                    function (response) {
                                                        $scope.msg = "Service not Exists";
                                                        $scope.statusval = response.status;
                                                        $scope.statustext = response.statusText;
                                                        $scope.headers = response.headers();
                                                            });
                                                        };
                                                    });
                                                    console.log("hi champ");
                                                
                                                