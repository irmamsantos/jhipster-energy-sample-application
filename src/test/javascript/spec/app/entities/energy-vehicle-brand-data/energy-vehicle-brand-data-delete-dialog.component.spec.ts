/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVehicleBrandDataDeleteDialogComponent } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data-delete-dialog.component';
import { EnergyVehicleBrandDataService } from 'app/entities/energy-vehicle-brand-data/energy-vehicle-brand-data.service';

describe('Component Tests', () => {
    describe('EnergyVehicleBrandData Management Delete Component', () => {
        let comp: EnergyVehicleBrandDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyVehicleBrandDataDeleteDialogComponent>;
        let service: EnergyVehicleBrandDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVehicleBrandDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyVehicleBrandDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVehicleBrandDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVehicleBrandDataService);
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
