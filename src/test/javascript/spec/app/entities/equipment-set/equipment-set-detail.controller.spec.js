'use strict';

describe('Controller Tests', function() {

    describe('EquipmentSet Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEquipmentSet, MockMetric, MockPadSet;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEquipmentSet = jasmine.createSpy('MockEquipmentSet');
            MockMetric = jasmine.createSpy('MockMetric');
            MockPadSet = jasmine.createSpy('MockPadSet');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'EquipmentSet': MockEquipmentSet,
                'Metric': MockMetric,
                'PadSet': MockPadSet
            };
            createController = function() {
                $injector.get('$controller')("EquipmentSetDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'team7App:equipmentSetUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
