(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('EquipmentsetSearch', EquipmentsetSearch);

    EquipmentsetSearch.$inject = ['$resource'];

    function EquipmentsetSearch($resource) {
        var resourceUrl =  'api/_search/equipmentsets/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
