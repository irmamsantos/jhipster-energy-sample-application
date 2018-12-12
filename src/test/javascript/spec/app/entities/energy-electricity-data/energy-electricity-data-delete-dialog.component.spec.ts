/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyElectricityDataDeleteDialogComponent } from 'app/entities/energy-electricity-data/energy-electricity-data-delete-dialog.component';
import { EnergyElectricityDataService } from 'app/entities/energy-electricity-data/energy-electricity-data.service';

describe('Component Tests', () => {
    describe('EnergyElectricityData Management Delete Component', () => {
        let comp: EnergyElectricityDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyElectricityDataDeleteDialogComponent>;
        let service: EnergyElectricityDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyElectricityDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyElectricityDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyElectricityDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyElectricityDataService);
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
