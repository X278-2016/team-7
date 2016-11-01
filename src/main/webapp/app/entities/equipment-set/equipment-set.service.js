(function() {
    'use strict';
    angular
        .module('team7App')
        .factory('EquipmentSet', EquipmentSet);

    EquipmentSet.$inject = ['$resource'];

    function EquipmentSet ($resource) {
        var resourceUrl =  'api/equipment-sets/:id';

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
