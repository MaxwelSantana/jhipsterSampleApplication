import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Coletor } from './coletor.model';
import { ColetorPopupService } from './coletor-popup.service';
import { ColetorService } from './coletor.service';

@Component({
    selector: 'jhi-coletor-delete-dialog',
    templateUrl: './coletor-delete-dialog.component.html'
})
export class ColetorDeleteDialogComponent {

    coletor: Coletor;

    constructor(
        private coletorService: ColetorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.coletorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'coletorListModification',
                content: 'Deleted an coletor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-coletor-delete-popup',
    template: ''
})
export class ColetorDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coletorPopupService: ColetorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.coletorPopupService
                .open(ColetorDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
