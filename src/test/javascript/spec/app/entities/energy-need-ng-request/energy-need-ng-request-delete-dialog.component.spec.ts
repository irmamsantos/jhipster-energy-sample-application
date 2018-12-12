/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNeedNGRequestDeleteDialogComponent } from 'app/entities/energy-need-ng-request/energy-need-ng-request-delete-dialog.component';
import { EnergyNeedNGRequestService } from 'app/entities/energy-need-ng-request/energy-need-ng-request.service';

describe('Component Tests', () => {
    describe('EnergyNeedNGRequest Management Delete Component', () => {
        let comp: EnergyNeedNGRequestDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyNeedNGRequestDeleteDialogComponent>;
        let service: EnergyNeedNGRequestService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNeedNGRequestDeleteDialogComponent]
            })
                .overrideTemplate(EnergyNeedNGRequestDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNeedNGRequestDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNeedNGRequestService);
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
