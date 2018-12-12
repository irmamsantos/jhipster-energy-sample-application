/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyContractedPowerRatingDataDeleteDialogComponent } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data-delete-dialog.component';
import { EnergyContractedPowerRatingDataService } from 'app/entities/energy-contracted-power-rating-data/energy-contracted-power-rating-data.service';

describe('Component Tests', () => {
    describe('EnergyContractedPowerRatingData Management Delete Component', () => {
        let comp: EnergyContractedPowerRatingDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyContractedPowerRatingDataDeleteDialogComponent>;
        let service: EnergyContractedPowerRatingDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyContractedPowerRatingDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyContractedPowerRatingDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyContractedPowerRatingDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyContractedPowerRatingDataService);
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
