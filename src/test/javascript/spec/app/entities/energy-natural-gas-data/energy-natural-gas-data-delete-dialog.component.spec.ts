/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyNaturalGasDataDeleteDialogComponent } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data-delete-dialog.component';
import { EnergyNaturalGasDataService } from 'app/entities/energy-natural-gas-data/energy-natural-gas-data.service';

describe('Component Tests', () => {
    describe('EnergyNaturalGasData Management Delete Component', () => {
        let comp: EnergyNaturalGasDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyNaturalGasDataDeleteDialogComponent>;
        let service: EnergyNaturalGasDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyNaturalGasDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyNaturalGasDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyNaturalGasDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyNaturalGasDataService);
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
