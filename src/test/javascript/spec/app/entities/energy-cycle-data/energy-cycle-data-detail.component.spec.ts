/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterEnergySampleApplicationTestModule } from '../../../test.module';
import { EnergyCycleDataDetailComponent } from 'app/entities/energy-cycle-data/energy-cycle-data-detail.component';
import { EnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

describe('Component Tests', () => {
    describe('EnergyCycleData Management Detail Component', () => {
        let comp: EnergyCycleDataDetailComponent;
        let fixture: ComponentFixture<EnergyCycleDataDetailComponent>;
        const route = ({ data: of({ energyCycleData: new EnergyCycleData(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterEnergySampleApplicationTestModule],
                declarations: [EnergyCycleDataDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnergyCycleDataDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnergyCycleDataDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.energyCycleData).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
