/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleModelDataDeleteDialogComponent } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data-delete-dialog.component';
import { EnergyVehicleModelDataService } from 'app/entities/energy-vehicle-model-data/energy-vehicle-model-data.service';

describe('Component Tests', () => {
    describe('EnergyVehicleModelData Management Delete Component', () => {
        let comp: EnergyVehicleModelDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyVehicleModelDataDeleteDialogComponent>;
        let service: EnergyVehicleModelDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleModelDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyVehicleModelDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVehicleModelDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleModelDataService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
