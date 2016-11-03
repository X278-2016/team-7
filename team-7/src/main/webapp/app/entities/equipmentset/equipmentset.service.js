(function() {
    'use strict';
    angular
        .module('team7App')
        .factory('Equipmentset', Equipmentset);

    Equipmentset.$inject = ['$resource'];

    function Equipmentset ($resource) {
        var resourceUrl =  'api/equipmentsets/:id';

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
