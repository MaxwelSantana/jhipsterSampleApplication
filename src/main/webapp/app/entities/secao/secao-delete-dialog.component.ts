import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Secao } from './secao.model';
import { SecaoPopupService } from './secao-popup.service';
import { SecaoService } from './secao.service';

@Component({
    selector: 'jhi-secao-delete-dialog',
    templateUrl: './secao-delete-dialog.component.html'
})
export class SecaoDeleteDialogComponent {

    secao: Secao;

    constructor(
        private secaoService: SecaoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.secaoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'secaoListModification',
                content: 'Deleted an secao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-secao-delete-popup',
    template: ''
})
export class SecaoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private secaoPopupService: SecaoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.secaoPopupService
                .open(SecaoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
