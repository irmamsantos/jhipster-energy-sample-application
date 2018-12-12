import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserSAC } from 'app/shared/model/user-sac.model';
import { UserSACService } from './user-sac.service';

@Component({
    selector: 'jhi-user-sac-delete-dialog',
    templateUrl: './user-sac-delete-dialog.component.html'
})
export class UserSACDeleteDialogComponent {
    userSAC: IUserSAC;

    constructor(private userSACService: UserSACService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userSACService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'userSACListModification',
                content: 'Deleted an userSAC'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-sac-delete-popup',
    template: ''
})
export class UserSACDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userSAC }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UserSACDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.userSAC = userSAC;
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
