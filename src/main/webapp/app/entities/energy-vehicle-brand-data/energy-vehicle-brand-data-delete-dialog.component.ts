import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';
import { EnergyVehicleBrandDataService } from './energy-vehicle-brand-data.service';

@Component({
    selector: 'jhi-energy-vehicle-brand-data-delete-dialog',
    templateUrl: './energy-vehicle-brand-data-delete-dialog.component.html'
})
export class EnergyVehicleBrandDataDeleteDialogComponent {
    energyVehicleBrandData: IEnergyVehicleBrandData;

    constructor(
        private energyVehicleBrandDataService: EnergyVehicleBrandDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyVehicleBrandDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyVehicleBrandDataListModification',
                content: 'Deleted an energyVehicleBrandData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-vehicle-brand-data-delete-popup',
    template: ''
})
export class EnergyVehicleBrandDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVehicleBrandData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyVehicleBrandDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyVehicleBrandData = energyVehicleBrandData;
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
