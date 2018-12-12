/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyCycleDataComponent } from 'app/entities/energy-cycle-data/energy-cycle-data.component';
import { EnergyCycleDataService } from 'app/entities/energy-cycle-data/energy-cycle-data.service';
import { EnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

describe('Component Tests', () => {
    describe('EnergyCycleData Management Component', () => {
        let comp: EnergyCycleDataComponent;
        let fixture: ComponentFixture<EnergyCycleDataComponent>;
        let service: EnergyCycleDataService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyCycleDataComponent],
                providers: []
            })
                .overrideTemplate(EnergyCycleDataComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnergyCycleDataComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnergyCycleDataService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnergyCycleData(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.energyCycleData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
