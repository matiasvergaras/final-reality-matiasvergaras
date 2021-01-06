package com.github.matiasvergaras.finalreality.factory.Weapons;

/**
 * Weapon Factory.
 * <p> An Abstract Weapon Factory that holds the common methods related to the production of every kind of weapons.</p>
 * <p> To avoid overcomplicating the code at a stage when it is not necessary yet (and won't be for a long time),
 * all specific character factories will inherit the setMagicPower method from this class, even if their creations do
 * not use them. This will also allow us to avoid using downcast at the Controller. </p>
 * <p> Factories will allow us to avoid tight coupling between the controller and the concrete products. This way,
 * Controller will request characters from the factories, who will make the corresponding calls to the model
 * constructors. </p>
 * <p> We will use the Singleton Pattern with each concrete Factory, since there should not be more than 1 instance
 * of each of them at the same time (given the methods to configure them without need to create another one). </p>
 * @since Homework 2
 * @author Matías Vergara Silva
 */
public abstract class WeaponFactory implements IWeaponFactory {
    int weight;
    int power;
    int magicPower;

    /**
     * Constructor for a new WeaponFactory.
     * @param name      The default name that the weapons produced by this factory will have.
     * @param power     The default Power value that the weapons produced by this factory will have.
     * @param weight    The default Weight value that the weapons produced by this factory will have.
     */
    public WeaponFactory(int power, int weight){
        this.weight = weight;
        this.power = power;
    }

    /**
     * {@inheritDoc}
     * @param weight    The new weight to set as default value.
     */
    public void setWeight(int weight){
        this.weight = weight;
    }

    /**
     * {@inheritDoc}
     * @param power     The new power to set as default value.
     */
    public void setPower(int power){
        this.power = power;
    }

    /**
     * {@inheritDoc}
     * @param magicPower    The new magicPower to set as default value.
     */
    public void setMagicPower(int magicPower){
        this.magicPower = magicPower;
    }

    /**
     * {@inheritDoc}
     * <p> Base behavior to be overwritten by magic weapons factories. </p>
     */
    public boolean isMagic(){
        return false;
    }
}
