/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNRStateHistoryDeleteDialogComponent } from 'app/entities/energy-nr-state-history/energy-nr-state-history-delete-dialog.component';
import { EnergyNRStateHistoryService } from 'app/entities/energy-nr-state-history/energy-nr-state-history.service';

describe('Component Tests', () => {
    describe('EnergyNRStateHistory Management Delete Component', () => {
        let comp: EnergyNRStateHistoryDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyNRStateHistoryDeleteDialogComponent>;
        let service: EnergyNRStateHistoryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNRStateHistoryDeleteDialogComponent]
            })
                .overrideTemplate(EnergyNRStateHistoryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNRStateHistoryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNRStateHistoryService);
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
