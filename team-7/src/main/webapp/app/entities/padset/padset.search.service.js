(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('PadsetSearch', PadsetSearch);

    PadsetSearch.$inject = ['$resource'];

    function PadsetSearch($resource) {
        var resourceUrl =  'api/_search/padsets/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
