import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';
import { EnergyVehicleModelDataService } from './energy-vehicle-model-data.service';
import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { EnergyVehicleBrandDataService } from 'app/entities/energy-vehicle-brand-data';

@Component({
    selector: 'jhi-energy-vehicle-model-data-update',
    templateUrl: './energy-vehicle-model-data-update.component.html'
})
export class EnergyVehicleModelDataUpdateComponent implements OnInit {
    energyVehicleModelData: IEnergyVehicleModelData;
    isSaving: boolean;

    energyvehiclebranddata: IEnergyVehicleBrandData[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private energyVehicleModelDataService: EnergyVehicleModelDataService,
        private energyVehicleBrandDataService: EnergyVehicleBrandDataService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyVehicleModelData }) => {
            this.energyVehicleModelData = energyVehicleModelData;
        });
        this.energyVehicleBrandDataService.query().subscribe(
            (res: HttpResponse<IEnergyVehicleBrandData[]>) => {
                this.energyvehiclebranddata = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyVehicleModelData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyVehicleModelDataService.update(this.energyVehicleModelData));
        } else {
            this.subscribeToSaveResponse(this.energyVehicleModelDataService.create(this.energyVehicleModelData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyVehicleModelData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyVehicleModelData>) => this.onSaveSuccess(),
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

    trackEnergyVehicleBrandDataById(index: number, item: IEnergyVehicleBrandData) {
        return item.id;
    }
}
