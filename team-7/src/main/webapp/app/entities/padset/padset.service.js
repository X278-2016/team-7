(function() {
    'use strict';
    angular
        .module('team7App')
        .factory('Padset', Padset);

    Padset.$inject = ['$resource'];

    function Padset ($resource) {
        var resourceUrl =  'api/padsets/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
