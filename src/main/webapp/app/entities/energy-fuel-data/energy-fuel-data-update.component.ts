import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';
import { EnergyFuelDataService } from './energy-fuel-data.service';

@Component({
    selector: 'jhi-energy-fuel-data-update',
    templateUrl: './energy-fuel-data-update.component.html'
})
export class EnergyFuelDataUpdateComponent implements OnInit {
    energyFuelData: IEnergyFuelData;
    isSaving: boolean;

    constructor(private energyFuelDataService: EnergyFuelDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyFuelData }) => {
            this.energyFuelData = energyFuelData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyFuelData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyFuelDataService.update(this.energyFuelData));
        } else {
            this.subscribeToSaveResponse(this.energyFuelDataService.create(this.energyFuelData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyFuelData>>) {
        result.subscribe((res: HttpResponse<IEnergyFuelData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
