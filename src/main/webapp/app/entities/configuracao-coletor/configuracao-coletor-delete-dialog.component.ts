import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ConfiguracaoColetor } from './configuracao-coletor.model';
import { ConfiguracaoColetorPopupService } from './configuracao-coletor-popup.service';
import { ConfiguracaoColetorService } from './configuracao-coletor.service';

@Component({
    selector: 'jhi-configuracao-coletor-delete-dialog',
    templateUrl: './configuracao-coletor-delete-dialog.component.html'
})
export class ConfiguracaoColetorDeleteDialogComponent {

    configuracaoColetor: ConfiguracaoColetor;

    constructor(
        private configuracaoColetorService: ConfiguracaoColetorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configuracaoColetorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'configuracaoColetorListModification',
                content: 'Deleted an configuracaoColetor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configuracao-coletor-delete-popup',
    template: ''
})
export class ConfiguracaoColetorDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private configuracaoColetorPopupService: ConfiguracaoColetorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.configuracaoColetorPopupService
                .open(ConfiguracaoColetorDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
