/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyCycleDataDeleteDialogComponent } from 'app/entities/energy-cycle-data/energy-cycle-data-delete-dialog.component';
import { EnergyCycleDataService } from 'app/entities/energy-cycle-data/energy-cycle-data.service';

describe('Component Tests', () => {
    describe('EnergyCycleData Management Delete Component', () => {
        let comp: EnergyCycleDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyCycleDataDeleteDialogComponent>;
        let service: EnergyCycleDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyCycleDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyCycleDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyCycleDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyCycleDataService);
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
