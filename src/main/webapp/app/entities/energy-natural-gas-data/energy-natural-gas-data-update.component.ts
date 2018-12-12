import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';
import { EnergyNaturalGasDataService } from './energy-natural-gas-data.service';

@Component({
    selector: 'jhi-energy-natural-gas-data-update',
    templateUrl: './energy-natural-gas-data-update.component.html'
})
export class EnergyNaturalGasDataUpdateComponent implements OnInit {
    energyNaturalGasData: IEnergyNaturalGasData;
    isSaving: boolean;

    constructor(private energyNaturalGasDataService: EnergyNaturalGasDataService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ energyNaturalGasData }) => {
            this.energyNaturalGasData = energyNaturalGasData;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.energyNaturalGasData.id !== undefined) {
            this.subscribeToSaveResponse(this.energyNaturalGasDataService.update(this.energyNaturalGasData));
        } else {
            this.subscribeToSaveResponse(this.energyNaturalGasDataService.create(this.energyNaturalGasData));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEnergyNaturalGasData>>) {
        result.subscribe(
            (res: HttpResponse<IEnergyNaturalGasData>) => this.onSaveSuccess(),
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
