/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyTariffDataDeleteDialogComponent } from 'app/entities/energy-tariff-data/energy-tariff-data-delete-dialog.component';
import { EnergyTariffDataService } from 'app/entities/energy-tariff-data/energy-tariff-data.service';

describe('Component Tests', () => {
    describe('EnergyTariffData Management Delete Component', () => {
        let comp: EnergyTariffDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyTariffDataDeleteDialogComponent>;
        let service: EnergyTariffDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyTariffDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyTariffDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyTariffDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyTariffDataService);
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
