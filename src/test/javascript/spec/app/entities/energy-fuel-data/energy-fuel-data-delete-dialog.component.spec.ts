/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyFuelDataDeleteDialogComponent } from 'app/entities/energy-fuel-data/energy-fuel-data-delete-dialog.component';
import { EnergyFuelDataService } from 'app/entities/energy-fuel-data/energy-fuel-data.service';

describe('Component Tests', () => {
    describe('EnergyFuelData Management Delete Component', () => {
        let comp: EnergyFuelDataDeleteDialogComponent;
        let fixture: ComponentFixture<EnergyFuelDataDeleteDialogComponent>;
        let service: EnergyFuelDataService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyFuelDataDeleteDialogComponent]
            })
                .overrideTemplate(EnergyFuelDataDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyFuelDataDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyFuelDataService);
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
