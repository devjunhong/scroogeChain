import java.util.ArrayList;
import java.security.PublicKey;

public class TxHandler {
    private UTXOPool current;
    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
        current = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
        Validity validity = new Validity(tx); 

        return validity.isValid();
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
    }

    public class Validity{
        private Transaction _tx;

        public Validity(Transaction tx){
            _tx = tx;
        }

        public boolean isValid(){

        }

        /**
         * (1) all outputs claimed by {@code tx} 
         * are in the current UTXO pool, 
         **/
        public boolean isInUTXOpool(){
            ArrayList<Transaction.Output> outputs = _tx.getOutputs(); 
            ArrayList<UTXO> utxos = current.getAllUTXO();

            for(int i = 0; i < utxos.size(); i++){

            }
        }

        /**
         * (2) the signatures on each input of 
         * {@code tx} are valid
         **/
        public boolean areInputsValid(){
            ArrayList<Transaction.Input> inputs = _tx.getInputs();
            for(int i = 0; i < inputs.size(); i++){
                Transaction.Output output = _tx.getOutput(i);
                Transaction.Input input = _tx.getInput(i); 

                PublicKey address = output.address;
                byte[] msg = _tx.getRawDataToSign(i);
                byte[] sig = input.signature;
                
                if(!Crypto.verifySignature(address, msg, sig))
                    return false;
            }
            
            return true;
        }

        /**
         * (3) no UTXO is claimed multiple times by {@code tx},
         **/

        /**
         * (4) Check the all {@code tx}s output values 
         * are non-negative
         **/
        public boolean isNegativeOutput(){
            ArrayList<Transaction.Output> outputs = _tx.getOutputs(); 
            for(int i = 0; i < outputs.size(); i++){
                if(outputs[i].value < 0)
                    return true;
            }
        }

        /**
         * (5) the sum of {@code tx}s input values is 
         * greater than or equal to the sum of its output
         **/
        public boolean sumInputGTE(){
            
        }
    }
}
