import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { EnergyVehicleBrandDataService } from './energy-vehicle-brand-data.service';

@Component({
    selector: 'jhi-energy-vehicle-brand-data-update',
    templateUrl: './energy-vehicle-brand-data-update.component.html'
})
export class EnergyVehicleBrandDataUpdateComponent implements OnInit {
    energyVehicleBrandData: IEnergyVehicleBrandData;
    isSaving: boolean;

    constructor(private energyVehicleBrandDataService: EnergyVehicleBrandDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyVehicleBrandData }) => {
            this.energyVehicleBrandData = energyVehicleBrandData;
        });
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
}
