import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { EnergyVehicleBrandDataService } from './energy-vehicle-brand-data.service';
import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';
import { EnergyVehicleModelDataService } from 'app/entities/energy-vehicle-model-data';

@Component({
    selector: 'jhi-energy-vehicle-brand-data-update',
    templateUrl: './energy-vehicle-brand-data-update.component.html'
})
export class EnergyVehicleBrandDataUpdateComponent implements OnInit {
    energyVehicleBrandData: IEnergyVehicleBrandData;
    isSaving: boolean;

    energyvehiclemodeldata: IEnergyVehicleModelData[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private energyVehicleBrandDataService: EnergyVehicleBrandDataService,
        private energyVehicleModelDataService: EnergyVehicleModelDataService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyVehicleBrandData }) => {
            this.energyVehicleBrandData = energyVehicleBrandData;
        });
        this.energyVehicleModelDataService.query().subscribe(
            (res: HttpResponse<IEnergyVehicleModelData[]>) => {
                this.energyvehiclemodeldata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyVehicleBrandData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyVehicleBrandDataService.update(this.energyVehicleBrandData));
        } else {
            this.subscribeToSaveResponse(this.energyVehicleBrandDataService.create(this.energyVehicleBrandData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyVehicleBrandData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyVehicleBrandData>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEnergyVehicleModelDataById(index: number, item: IEnergyVehicleModelData) {
        return item.id;
    }
}
