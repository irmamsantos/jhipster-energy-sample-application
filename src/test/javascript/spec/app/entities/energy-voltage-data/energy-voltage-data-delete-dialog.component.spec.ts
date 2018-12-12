/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyVoltageDataDeleteDialogComponent } from 'app/entities/energy-voltage-data/energy-voltage-data-delete-dialog.component';
import { EnergyVoltageDataService } from 'app/entities/energy-voltage-data/energy-voltage-data.service';

describe('Component Tests', () => {
    describe('EnergyVoltageData Management Delete Component', () => {
        let comp: EnergyVoltageDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyVoltageDataDeleteDialogComponent>;
        let service: EnergyVoltageDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyVoltageDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyVoltageDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyVoltageDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyVoltageDataService);
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
