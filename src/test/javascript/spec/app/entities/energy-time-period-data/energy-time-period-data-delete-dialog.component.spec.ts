/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTimePeriodDataDeleteDialogComponent } from 'app/entities/energy-time-period-data/energy-time-period-data-delete-dialog.component';
import { EnergyTimePeriodDataService } from 'app/entities/energy-time-period-data/energy-time-period-data.service';

describe('Component Tests', () => {
    describe('EnergyTimePeriodData Management Delete Component', () => {
        let comp: EnergyTimePeriodDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyTimePeriodDataDeleteDialogComponent>;
        let service: EnergyTimePeriodDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTimePeriodDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyTimePeriodDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyTimePeriodDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyTimePeriodDataService);
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
