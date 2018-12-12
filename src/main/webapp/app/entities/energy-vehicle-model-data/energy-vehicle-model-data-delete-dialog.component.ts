import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyVehicleModelData } from 'app/shared/model/energy-vehicle-model-data.model';
import { EnergyVehicleModelDataService } from './energy-vehicle-model-data.service';

@Component({
    selector: 'jhi-energy-vehicle-model-data-delete-dialog',
    templateUrl: './energy-vehicle-model-data-delete-dialog.component.html'
})
export class EnergyVehicleModelDataDeleteDialogComponent {
    energyVehicleModelData: IEnergyVehicleModelData;

    constructor(
        private energyVehicleModelDataService: EnergyVehicleModelDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyVehicleModelDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyVehicleModelDataListModification',
                content: 'Deleted an energyVehicleModelData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-vehicle-model-data-delete-popup',
    template: ''
})
export class EnergyVehicleModelDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVehicleModelData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyVehicleModelDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyVehicleModelData = energyVehicleModelData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
